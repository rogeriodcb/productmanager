import React, {useState, useEffect} from 'react';
import {Link, useHistory} from 'react-router-dom';
import {FiPower, FiEdit, FiTrash2} from 'react-icons/fi';
import './styles.css';
import api from '../../services/api'
import logoImage from '../../assets/logo.png'

export default function Products(){

    const [products, setProducts] = useState([]);

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const history = useHistory(); 
    
    // logout function
    async function logout(){
        localStorage.clear();
        history.push('/'); 
    }

    // edit function
    async function editProduct(id){
        try {
            history.push(`/product/new/${id}`)
        } catch (error) {
            alert('Edit failed! Try again.');
        }
    }

    // delete function
    async function deleteProduct(id){
        try {
            await api.delete(`/product-api/v1/products/${id}`,{
                headers:{
                    Authorization: `Bearer ${accessToken}`
                }
            })

            setProducts(products.filter(product=>product.id !==id))
        } catch (error) {
            alert('Delete failed! Try again.');
        }
    }

    useEffect(()=>{
        api.get('/product-api/v1/products',{
            headers:{
                Authorization: `Bearer ${accessToken}`
            }
        }).then(response=>{
            setProducts(response.data)
        })
    })
    
    if(!products){
        return(
            <div className="product-container">
                <header>
                    <img src={logoImage} alt="Erudio"/>
                    <span>Welcome, <strong>{username.toUpperCase()}</strong>!</span>
                    <Link className="button" to="product/new/0">Add New Product</Link>
                    <button onClick={logout} type="button">
                        <FiPower size={18} color ="#251FC5"/>
                    </button>
                </header>
                <h1>No Registered Products found.</h1>
            </div>
        );
    }
    else{
    return (
        <div className="product-container">

            <header>
                <img src={logoImage} alt="Erudio"/>
                <span>Welcome, <strong>{username.toUpperCase()}</strong>!</span>
                <Link className="button" to="product/new/0">Add New Product</Link>
                <button onClick={logout} type="button">
                    <FiPower size={18} color ="#251FC5"/>
                </button>
            </header>
            <h1>Registered Products</h1>
        
            <ul>
                {products.map(product=>(
                    <li key={product.id}>
                        <strong>Name:</strong>
                        <p>{product.nome}</p>
                        <strong>Description:</strong>
                        <p>{product.descricao}</p>
                        <strong>Price:</strong>
                        <p>{Intl.NumberFormat('pt-BR',{style:'currency',currency:'BRL'}).format(product.valor.replace(',','.'))}</p>
                            
                        <button onClick={()=>editProduct(product.id)} type="button">
                            <FiEdit size={20} color="#251FC5"/>
                        </button>
                            
                        <button onClick={()=>deleteProduct(product.id)} type="button">
                            <FiTrash2 size={20} color="#251FC5"/>
                        </button>
                    </li>
                ))}
            </ul>

        </div>
        );
                
    }
}