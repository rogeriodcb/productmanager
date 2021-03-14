import React from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';

import Login from './pages/Login';
import Products from './pages/Products';
import NewProduct from './pages/NewProduct'

export default function Routes(){
    return (
 

        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login}/>
                <Route path="/products" component={Products}/>
                <Route path="/product/new/:productId" component={NewProduct}/>
            </Switch>
        </BrowserRouter>

    );
}