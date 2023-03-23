#include <iostream>
#include <cmath>
using namespace std;

class simp_calc
{
private:
    int a, b;
    int c;

protected:
    void simp_set()
    {
        cout << "Enter your first value " << endl;
        cin >> a;
        cout << "Enter your second value " << endl;
        cin >> b;
        cout << "Enter 1 for '+'." << endl;
        cout << "Enter 2 for '-'." << endl;
        cout << "Enter 3 for '*'." << endl;
        cout << "Enter 4 for '/'." << endl;
        cin >> c;
    }

public:
    void display_cal_sim()
    {
        simp_set();
        switch (c)
        {
        case 1:
            cout << "the addition of your value are. " << endl
                 << a + b << endl;

            break;
        case 2:
            cout << "the subtraction of your value are. " << endl
                 << a - b << endl;

            break;
        case 3:
            cout << "the multiplication of your value are. " << endl
                 << a * b << endl;

            break;
        case 4:
            cout << "the dividation of your value are. " << endl
                 << a / b << endl;

            break;
        default:
            cout << "     ERROR      " << endl
                 << endl;
            cout << "% Invaid value %" << endl;
            break;
        }
    }
};
class sci_clac
{
private:
    int m;
    int o;

protected:
    void sci_set()
    {
        cout << "Enter your first value " << endl;
        cin >> m;
        cout << "Enter 1 for squareroot." << endl;
        cout << "Enter 2 for absulate value." << endl;
        cout << "Enter 3 for natural log." << endl;
        cout << "Enter 4 for common log." << endl;
        cin >> o;
    }

public:
    void display_cal_sci()
    {
        sci_set();
        switch (o)
        {
        case 1:
            cout << "the square root of your value are. " << endl
                 << sqrt(m) << endl;

            break;
        case 2:
            cout << "the absulate value of your value are. " << endl
                 << abs(m) << endl
                 << endl;
            break;
        case 3:
            cout << "the natural log of your value are. " << endl
                 << log(m) << endl;

            break;
        case 4:
            cout << "the commomn log  of your value are. " << endl
                 << log10(m) << endl;

            break;
        default:
            cout << "     ERROR      " << endl
                 << endl;
            cout << "% Invaid value %" << endl;
            break;
        }
    }
};
class hybrid_cal : public simp_calc, public sci_clac
{
public:
    void dual_cla()
    {
        display_cal_sim();
        display_cal_sci();
    }
};
int main()
{
    hybrid_cal darsh;
    darsh.dual_cla();
    return 0;
}