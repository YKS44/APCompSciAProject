package products;

/**
 * If a product class implements this interface, the product will be movable in the {@link ui.pages.StorePage Store page}.
 */
public interface Movable {
    /**
     * Returns the amount of quantity to move as the product is transported. Each product has their max amount that can be lost, and it scales with percent expired.
     * @return Amount of quantity to be lost.
     */
    int move();
}
