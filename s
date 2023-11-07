#include <stdio.h>
#include <stdlib.h>

int main(void) {

  int N, valor, i, j;
  scanf("%d", &N);

  int **matriz = (int **)malloc(sizeof(int) * N);
  for (int i = 0; i < N; i++) {
  }

  for (i = 0; i < N; i++) {
    printf("| ");
    for (j = 0; j < N; j++) {
      if (i == j) {
        matriz[i][j] = 1;
      } else {
        matriz[i][j] = 0;
      }

      printf("%d ", matriz[i][j]);
    }
    printf("|\n");
  }

  for (i = 0; i < N; i++) {
    free(matriz[i]);
    // matriz = matriz[0][0], matriz[0][1], matriz[0][2], e matriz[0,3]
  }

  free(matriz);

  return 0;
}
