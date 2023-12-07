from math import sqrt,floor,ceil


def read_file(fileName):

    # returns array of tuples (time,distance)

    with  open(fileName,'r') as f:

        lineOne = f.readline()
        firstLine = lineOne.split()

        lineTwo = f.readline()
        secondLine = lineTwo.split()

        races = []
        for i in range(1,len(firstLine)):
            races.append((int(firstLine[i]),int(secondLine[i])))
        return races

def read_file2(fileName):

    with  open(fileName,'r') as f:

        lineOne = f.readline()
        firstLine = lineOne.split()

        lineTwo = f.readline()
        secondLine = lineTwo.split()

        firstLine = firstLine[1:len(firstLine)]
        firstLine = ''.join(firstLine)
        
        
        secondLine = secondLine[1:len(secondLine)]
        secondLine = ''.join(secondLine)

        return (int(firstLine),int(secondLine))  


def dontThinkSolution(race):

    n = 0
    for i in range(race[0]):
        if (race[0]-i)*i > race[1]:
            n += 1
    return n

def dontThinkAtAll(races):
    n = 1
    for race in races:
        n *= dontThinkSolution(race)
    return n

def opgave2(race):
    D =  sqrt(race[0]**2-4*race[1])

    p1 = (race[0]-D)/2
    p2 = (race[0]+D)/2

    return floor(p2)-floor(p1)


if __name__ == "__main__":
    races = read_file2("opgave.txt")
    print(races)
    #print(dontThinkAtAll(races))
    print(opgave2(races))
