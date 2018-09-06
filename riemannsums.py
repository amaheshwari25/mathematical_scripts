import numpy as np

def riemannSum():
    """
    Calculates the Riemann sum of an inputted function, across a definite range, with the given number of intervals
    (for uniform  intervals)

    tbd:
    - exact values?
    - upper or lower RS?

    :return: the Riemann sum given the parameters
    """

    function = input("input function:")
    bounds = input("input bounds for riemann sum as csv").split(",")

    bounds[0] = eval(bounds[0])
    bounds[1] = eval(bounds[1])
    interCount = int(input("input number of intervals for evaluation"))
    sumType = input("input left for LHRS, right for RHRS, trap for Trapezoidal Rule, mid for Midpoint Rule")

    endpoints = np.linspace(bounds[0], bounds[1], interCount+1)

    if sumType == "left":
        endpoints = endpoints[:-1]
    elif sumType == "right":
        endpoints = endpoints[1:]
    elif not (sumType == "trap" or sumType == "mid"):
        print("InputError: Unacceptable sum type.")
        return None

    riemSum = 0
    width = (bounds[1]-bounds[0])/interCount
    if sumType == "trap":
        for index in np.arange(1, len(endpoints)):
            x = endpoints[index-1]
            val1 = eval(function)
            x = endpoints[index]
            val2 = eval(function)
            riemSum += 0.5 * (val1 + val2)
    elif sumType == "mid":
        for index in np.arange(1, len(endpoints)):
            x = 0.5*(endpoints[index-1]+endpoints[index])
            riemSum += eval(function)
    else:
        for point in endpoints:
            x = point
            riemSum += eval(function)
    riemSum *= width
    return riemSum

print(riemannSum())

