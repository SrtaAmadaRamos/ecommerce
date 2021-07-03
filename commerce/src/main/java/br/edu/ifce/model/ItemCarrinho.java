package br.edu.ifce.model;

import br.edu.ifce.utils.StringUtils;

public class ItemCarrinho {
    private Item item;
    private int itemId;
    private int quantidade;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        this.itemId = item.getId();
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal()
    {
        return item.getPreco() * quantidade;
    }
    public String getPrecoFormatado()
    {
        return StringUtils.FormatarParaDinheiro(getValorTotal());
    }
}
