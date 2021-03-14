import React, {useState} from 'react';
import {useHistory} from 'react-router-dom';
import './styles.css';

import api from '../../services/api'


import logoImage from '../../assets/logo.png'
import padlock from '../../assets/padlock.png'

export default function Login(){
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const history = useHistory();
    


    async function login(e){
        e.preventDefault();

        const params = new URLSearchParams();
        params.append('grant_type', 'password');
        params.append('username', username);
        params.append('password', password);
        const token = Buffer.from('product:product@123', 'utf8').toString('base64');

        try{
            
            const response = await api.post('/oauth/token', params,{
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Authorization': `Basic ${token}`
                  }
            });
            localStorage.setItem('username',username);
            localStorage.setItem('accessToken',response.data.access_token);
            history.push('/products');
            
        }catch(err){
            alert('Login failed ! Try again !');
        }
    };

    return (
        <div className="login-container">
            <section className="form">
                <img src={logoImage} alt="Erudio Logo"/>
                <form onSubmit ={login}>
                    <h1>Access your Account</h1>
                    <input 
                        placeholder="Username"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                    />
                    <input 
                        type="password" 
                        placeholder="Password" 
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />

                    <button className="button" type="submit">Login </button>

                </form>
            </section>
            <img src={padlock} alt="Login"/>
        </div>


        
        )
}