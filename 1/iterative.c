#include <stdio.h>

int main() {
    int N = 10, i = 0;
    char characters[N];

    for(; i < N; i++) {
        int c = getchar();
        if(c == '\n') break;
        characters[i] = c;
    }

    for(--i; i >= 0; i--) printf("%c", characters[i]);
    printf("\n");
    return 0;
}