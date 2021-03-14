import React, {useState, useEffect} from 'react';
import { useHistory, Link, useParams} from 'react-router-dom';
import {FiArrowLeft} from 'react-icons/fi';

import api from '../../services/api'

import './styles.css';

import logoImage from '../../assets/logo.png';


export default function NewProduct(){

    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    
    const {productId} = useParams();

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const history = useHistory();

    async function loadProduct(){
        try {
            const response = await api.get(`/product-api/v1/products/${productId}`,{
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                    'Content-Type': 'application/json'
                }

            })

            setId(response.data.id);
            setName(response.data.nome);
            setDescription(response.data.descricao);
            setPrice(response.data.valor);

        } catch (error) {
            alert('Error recovering product! Try again.');
            history.push('/products');
        }
    }

    useEffect(()=>{
        if (productId === '0') return;      
        else loadProduct();
    }, [productId])

    async function saveOrUpdate(e){
        e.preventDefault();

        const data ={
            'nome': name,
            'descricao':description,
            'valor': price,
        }

        try {
            if (productId === '0') {
                await api.post("/product-api/v1/product",data,{
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                        'Content-Type': 'application/json'
                    }
                });    
            } else {
                data.id = id;
                await api.put(`/product-api/v1/products/${id}`,data,{
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                        'Content-Type': 'application/json'
                    }
                });
            } 
            
            history.push('/Products');
               
        } catch (error) {
            alert('Error while recording Product! Please, try again.')
        }
        
    }
    return (
        <div className="new-product-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="Erudio"/>
                    <h1>{productId === '0' ? 'Add New' : 'Update'} Product</h1>
                    <p>Enter the product information and click on {productId === '0' ? "'Add'" : "'Update'"}! </p>
                    <Link className="back-link" to="/products">
                        <FiArrowLeft size={16} color="#251fc5"/>
                        Back to Products    
                    </Link>
                </section>

                <form onSubmit={saveOrUpdate}>
                    <input
                        placeholder="Name"
                        value= {name}
                        onChange={e=>setName(e.target.value)}
                    />
                    <input
                        placeholder="Description"
                        value= {description}
                        onChange={e=>setDescription(e.target.value)}
                    />
                    <input
                        placeholder="Price"
                        value= {price}
                        onChange={e=>setPrice(e.target.value)}
                    />

                    <button className="button" type="submit">{productId === '0' ? 'Add' : 'Update'}</button>
                </form>
            </div>    
        </div>

    );
}