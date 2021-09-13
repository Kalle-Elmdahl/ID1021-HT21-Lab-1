/*
    Author: Kalle Elmdahl 21/09/07 (updated 21/09/09)
    The code is used for reversing strings.
    It takes a command-line input and prints it out in reverse using the "printCharactersReverse" function.
    The code is based on an assignment from the KTH-course ID1020
*/

#include <stdio.h>

/*
    Uses the command-line and reverses the input after the enter key is pressed
*/
void printCharactersReverse();

/*
    Used for testing the "printCharactersReverse" function
    The function makes it possible to repeatedly reverse strings without restarting the program
*/
static void test();

/*
    Program startup
*/
int main() {
    test();
    return 0;
}

void printCharactersReverse() {
    int character = getchar();
    if(character == '\n') return;
    printCharactersReverse();
    printf("%c", character);
}

static void test() {
    printCharactersReverse();
    printf("\n\n Again? [y/n]: ");
    int c = getchar();
    if(c == 'y') {
        getchar();
        test();
    }
}