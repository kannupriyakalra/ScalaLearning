package Hackerrank

//https://www.hackerrank.com/challenges/area-under-curves-and-volume-of-revolving-a-curv/problem?isFullScreen=true&h_r=next-challenge&h_v=zen

/*for understanding area under the curve approximation- https://www.google.com/search?q=area+under+the+curve+approximation&sca_esv=571585710&rlz=1C5GCEM_enGB1013GB1014&sxsrf=AM9HkKluC90e3rqUQp3GW1HffaSWLR8ZrA%3A1696700335191&ei=r5chZbmnC_KVhbIP_qOE0AE&oq=area+under+the+curve+app&gs_lp=Egxnd3Mtd2l6LXNlcnAiGGFyZWEgdW5kZXIgdGhlIGN1cnZlIGFwcCoCCAEyBRAAGIAEMgYQABgWGB4yBhAAGBYYHjIGEAAYFhgeMggQABgWGB4YDzIGEAAYFhgeMgYQABgWGB4yBhAAGBYYHjIGEAAYFhgeMgYQABgWGB5I2ypQ2wlYzRdwAXgBkAEAmAFJoAGUAqoBATS4AQPIAQD4AQHCAgoQABhHGNYEGLADwgIKEAAYigUYsAMYQ8ICBxAAGIoFGEPCAggQABiKBRiRAuIDBBgAIEGIBgGQBgo&sclient=gws-wiz-serp

Area under the curve = sum of area of small rectangle of width 0.001 on x axis

length- value of y coordinate corresponding to x coordinate, y = f(x)

Area under the curve = A1 + A2 + A3 + ... + An , A1, A2 are small rectangle of width 0.001 on x axis,
                       = y1 * 0.001 + y2 * 0.001 + y3 * 0.001 + ... + yn * 0.001
                       = f(x1) * 0.001 + f(x2) * 0.001 + f(x3) * 0.001 + ... + f(xn) * 0.001 , x1, x2 are values on x-axis incrementing by 0.001.
                       = ( f(x1) + f(x2) + f(x3)  + ... + f(xn) ) * 0.001
                       = ys.sum * 0.001 in line 50

y = f(x), f is the function of curve, y means point on y-axis, area under the curve = y * 0.001 where y varies as x lies b/w a specific range of L and R.

1. find different values of x
2. find y corresponding to each x
3. find sum of all y
4. Area under the curve = ys.sum * 0.001
 */
object AreaUnderCurvesAndVolumeOfRevolvingACurve extends App {

  // This function will be used while invoking "Summation" to compute the area under the curve. Value of function f is calculated here.
  //this function is f in y = f(x) = (a1)x^b1 + (a2)x^b2 + (a3)x^b3 + ... + (an)x^bn
  def f(coefficients: List[Int], powers: List[Int], x: Double): Double = {
    //Fill Up this function body to compute the value of the function for the given coefficients, powers and value of x.
    coefficients.zip(powers).foldLeft(0.0) { case (acc, (a, b)) => acc + a * Math.pow(x, b) }

    //coefficients.zip(powers) gives List[(Int, Int)]
  }

  // This function will be used while invoking "Summation" to compute the Volume of revolution of the curve around the X-Axis. The 'Area' function referred to here is for calculating the area of the
  // circle obtained by rotating the point on the curve (x,y) = (x,f(x)) around the X-Axis.

  // area is (pi radius ^2 ) = (pi y ^2 ), pi = 3.1415926535 , y is radius
  // volume of cylinder- area * h = pi r^2 * h
  def area(coefficients: List[Int], powers: List[Int], x: Double): Double = {
    //Fill up this function body to compute the area of the circle on revolving the point (x,f(x)) around the X-Axis for the given coefficients, powers and value of x.

    val y = Math.abs(f(coefficients, powers, x))
    3.1415926535 * Math.pow(y, 2)
  }

  def generate(upperLimit: Int, lowerLimit: Double, acc: List[Double]): List[Double] = { //acc is result list that has various values of x.
    if (lowerLimit > upperLimit) acc
    else generate(upperLimit, lowerLimit + 0.001, (lowerLimit + 0.001) :: acc)
  }

  // This is the part where the series of rectangle or cylinder is summed up. This function is invoked once with func = f ie y to compute the area under the curve.
  // Then it is invoked again with func = area to compute the volume of revolution of the curve.
  def summation(func: (List[Int], List[Int], Double) => Double, upperLimit: Int, lowerLimit: Int, coefficients: List[Int], powers: List[Int]): Double = {

    val xs: List[Double] = generate(upperLimit, lowerLimit, List(lowerLimit)) //xs means various x, create various values of x b/w L, R and put them in a list.
    // List(lowerLimit) is a list with 1 element as lowerLimit value.
    val ys: List[Double] = xs.map(x => func(coefficients, powers, x)) // corresponding y for each x based on func is f then y is List[length] or func is area then y is List[area],
    // here x is each element of xs: List[Double]
    ys.sum * 0.001
  }


  // The Input-Output functions will be handled by us. You only need to concentrate your effort on the function bodies above.
  // println(summation(f, 4, 1, List(1, 2, 3, 4, 5), List(6, 7, 8, 9, 10))) //called summation to calculate area under the curve
  // println(summation(area, 4, 1, List(1, 2, 3, 4, 5), List(6, 7, 8, 9, 10)) ) //called summation to calculate volume of revolution of the curve

  // println(summation(f, 20, 2, List(1, 2), List(0,1))) //o/p- 414.02299999997854
  //  println(summation(area, 20, 2, List(1, 2), List(0,1)) ) //o/p- 36024.18118125913

  println(summation(f, 10, 1, List(1, 2, 3, 4), List(0, 1, 2, 3)))
  println(summation(area, 10, 1, List(1, 2, 3, 4), List(0, 1, 2, 3))) //o/p- 8.620116179484966E7 which is same as 86142470.4 as E7 means 10^7 so move decimal position by 7 in 8.620116179484966E7 which is 86201161.79484966E7

}

//1 to 10 by 2, command click by, it means range is created with a step of 2 ie 1, 3, 5, 7, 10

/*
volume of the small cylinder solid- (pi radius ^2 ) * 0.001 = (pi y ^2 ) * 0.001 , where y varies as x lies b/w a specific range of L and R.
                                                                                 - radius is y as revolving around x-axis, width is 0.001 for the smallest cylinder

volume of revolving the curve = V1 + V2 + V3 + ... + Vn , V1, V2 are small cylinder of width 0.001 on x axis,
                              = (pi y1 ^2 ) * 0.001 + (pi y2 ^2 ) * 0.001 + (pi y3 ^2 ) * 0.001 + ... + (pi yn ^2 ) * 0.001
                              = (pi f(x1) ^2 ) * 0.001 + (pi f(x2) ^2 ) * 0.001 + (pi f(x3) ^2 ) * 0.001 + ... + (pi f(xn) ^2 ) * 0.001 , x1, x2 are values on x-axis incrementing by 0.001.
                              = ( pi f(x1) ^2 + pi f(x2) ^2 + pi f(x3) ^2  + ... + pi f(xn) ^2 ) * 0.001
                              = ys.sum * 0.001

*/