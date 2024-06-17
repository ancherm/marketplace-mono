import React from 'react';
import {NavLink, Route, Routes, Navigate} from "react-router-dom";
import {privateRoutes} from "../router";

const AppRouter = () => {
    return (
        <Routes>
            {privateRoutes.map(route =>
                <Route
                    element={<route.component />}
                    path={route.path}
                    exact={route.exact}
                    key={route.path}
                />
            )}
            <Route
                path="*"
                element={<Navigate to="/catalog" replace/>}
            />
        </Routes>
    );
};

export default AppRouter;