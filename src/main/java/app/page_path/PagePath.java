package app.page_path;

/**
 * This {@link Enum} encapsulate paths used in application
 */
public enum PagePath {
    ADD("/pages/addItem"),
    PRINT_CHECK("/pages/printCheck"),
    GOODS_ADD("/goodsAddServlet"),
    ADD_USER("/pages/addUser"),
    TERMS_ERROR("/errors/termsError"),
    COMPLETIVE_SERVLET("/complete"),
    HOME_PAGE("/pages/homePage"),
    LOGOUT_PAGE("app/logout"),
    EMPTY_BASKET_ERROR("/errors/emptyBasketError"),
    ERROR_PATH_404("/errors/notFoundError"),
    ERROR_PATH_User_NOT_FOUND("/errors/userNotFoundError");

    private final String path;

    PagePath(String path) {
        this.path=path;
    }

    public String getPath() {
        return path;
    }
}
