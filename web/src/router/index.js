import CatalogPage from "../pages/CatalogPage";
import AddProductPage from "../pages/seller/AddProductPage";

export const privateRoutes = [
    {path: `/catalog`, component: CatalogPage, exact: true},
    {path: `/add-product`, component: AddProductPage, exact: true},
    // {path: `/posts/:id`, component: PostIdPage, exact: true},
]
