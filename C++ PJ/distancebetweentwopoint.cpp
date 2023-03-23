#include <iostream>
#include <cmath>
using namespace std;

// float Square(float value);
class point
{
    int x, y;

public:
    point() {}
    point(int a, int b)
    {
        x = a;
        y = b;
    }
    void print()
    {
        cout << "the first point is " << x << " the second point is " << y << endl;
    }
    friend void distancebetween(point o1, point o2);
};
float square(float value)
{
    // Multiply value two times
    return value * value;
};
void distancebetween(point o1, point o2)
{
    // (x2 - x1) & (y2 - y1)
    int sq1 = o2.x - o1.x;
    int sq2 = o2.y - o1.y;
    // squaring (x2 - x1) & (y2 - y1)
    long double num1 = square(sq1);
    long double num2 = square(sq2);
    // adding both square roots
    long double num3 = num1 + num2;
    // square rooting (x2 - x1) & (y2 - y1)
    long double ans = sqrt(num3);
    // printing ans of the equation
    cout << "the distence between two point is " << ans << endl;
};
int main()
{
    int a, b, c, d;
    point point1, point2;
    cout << "enter value you want for co oradinates " << endl;
    cin >> a >> b >> c >> d;
    // point point1(a,b) , point2(c,d);
    point1 = point(a, b);
    point2 = point(c, d);
    point1.print();
    point2.print();
    distancebetween(point1, point2);

    return 0;
}