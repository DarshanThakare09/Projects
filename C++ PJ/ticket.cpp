#include <iostream>
#include <map>
using namespace std;

class passenger_info
{
public:
    string name, gender, date, email;
    int age;
    passenger_info(){};
    passenger_info(string a, string b, string c, string d, int g)
    {
        name = a;
        gender = b;
        date = c;
        email = d;
        age = g;
    }
};
class verification : virtual protected passenger_info
{
protected:
    void verificatoin()
    {
        if (age <= 18)
        {
            cout << "            Your age is not verified for Airtravel           " << endl;
            exit(0);
        }
        else
        {
        }
    }
};
class processing : virtual protected passenger_info, verification
{
private:
    string i;
    string s;

public:
    processing()
    {
        i = "817493069735669";
        s = "jdweugdujk";
    }
    void display()
    {
        verification();
        cout << "     ---------------------------------------------------------------------------------------" << endl;
        cout << "     |   Name : " << name << "                                                                    |" << endl;
        cout << "     |   Age : " << age << "                               COME SOOONN!!...                            |" << endl;
        cout << "     |   Gender : " << gender << "                                                                     |" << endl;
        cout << "     |   Date : " << date << "          Your seat No. is :" << s << i << "                |" << endl;
        cout << "     |   Email : " << email << "                                                            |" << endl;
        cout << "     ---------------------------------------------------------------------------------------" << endl;
    }
};
class ticket : virtual public passenger_info, processing
{
public:
    ticket(string a, string b, string c, string d, int g) : passenger_info(a, b, c, d, g){};
    void display()
    {
        processing();
        processing::display();
    }
};
int main()
{
    string a, b, c, d, e, f;
    int g;
    cout << "Enter Your Name:" << endl;
    cin >> a;
    cout << "Enter Your Gender:" << endl;
    cin >> b;
    cout << "Enter Your Date:" << endl;
    cin >> c;
    cout << "Enter Your Email:" << endl;
    cin >> d;
    cout << "Enter Your Age:" << endl;
    cin >> g;
    ticket darsh(a, b, c, d, g);
    darsh.ticket::display();
    return 0;
}