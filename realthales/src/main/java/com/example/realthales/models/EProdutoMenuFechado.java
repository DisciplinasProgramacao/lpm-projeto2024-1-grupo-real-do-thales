
package com.example.realthales.models;
 
    public enum EProdutoMenuFechado {
        FALAFEL_ASSADO(1),
        CACAROLA_LEGUMES(2),
        COPO_SUCO(3),
        REFRIGERANTE_ORGANICO(4),
        CERVEJA_VEGANA(5);
    
        private final int id;
    
        EProdutoMenuFechado(int id) {
            this.id = id;
        }
    
        public int getId() {
            return id;
        }
    
        public static boolean produtoEhValido(int idProduto) {
            for (EProdutoMenuFechado produto : values()) {
                if (produto.getId() == idProduto) {
                    return true;
                }
            }
            return false;
        }
    
        public static boolean ehComida(int idProduto) {
            return idProduto == FALAFEL_ASSADO.id || idProduto == CACAROLA_LEGUMES.id;
        }
    
        public static boolean ehBebida(int idProduto) {
            return idProduto == COPO_SUCO.id || idProduto == REFRIGERANTE_ORGANICO.id || idProduto == CERVEJA_VEGANA.id;
        }
    }

