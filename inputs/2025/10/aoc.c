#include <stdio.h>
#include <stdlib.h>
#include <glpk.h>

int main() {
    int n_rows, n_cols;
    if (scanf("%d%d", &n_rows, &n_cols) != 2) return 1;
    int *sum = malloc(n_rows * sizeof(int));
    for (int i = 0; i < n_rows; ++i) {
        if (scanf("%d", sum + i) != 1) return 1;
    }

    glp_term_out(GLP_OFF);
    glp_prob *lp = glp_create_prob();
    glp_iocp parm;
    glp_init_iocp(&parm);
    parm.presolve = GLP_ON;

    glp_add_cols(lp, n_cols);
    glp_add_rows(lp, n_rows);
    glp_set_obj_dir(lp, GLP_MIN);

    int *sz = malloc(n_cols * sizeof(int));
    int **rz = malloc(n_cols * sizeof(int*));
    for (int col = 1; col <= n_cols; ++col) {
        glp_set_col_bnds(lp, col, GLP_LO, 0.0, 0.0);
        glp_set_obj_coef(lp, col, 1.0);
        glp_set_col_kind(lp, col, GLP_IV);

        int size;
        if (scanf("%d", &size) != 1) return 1;
        sz[col - 1] = size;
        rz[col - 1] = malloc(size * sizeof(int));
        for (int i = 0; i < size; ++i) {
            if (scanf("%d", rz[col - 1] + i) != 1) return 1;
        }
    }

    int idx[20];
    double coef[20];

    for (int row = 0; row < n_rows; ++row) {
        glp_set_row_bnds(lp, row + 1, GLP_FX, sum[row], sum[row]);
        int count = 0;
        for (int col = 0; col < n_cols; ++col) {
            int size = sz[col];
            for (int i = 0; i < size; ++i) {
                if (rz[col][i] == row) {
                    ++count;
                    coef[count] = 1;
                    idx[count] = col + 1;
                }
            }
        }
        glp_set_mat_row(lp, row + 1, count, idx, coef);
    }

    glp_intopt(lp, &parm);
    printf("%d\n", (int) (glp_mip_obj_val(lp) + 1e-3));
    glp_delete_prob(lp);
    return 0;
}
