#include <stdio.h>
void printCharactersReverse();

int main() {
    printCharactersReverse();
    printf("\n");
    return 0;
}

void printCharactersReverse() {
    int character = getchar();
    if(character == '\n') return;
    printCharactersReverse();
    printf("%c", character);
}