import "./style.css";
import React, { useEffect, useState } from "react";
import imagemIcone from "../../../assets/imagem.png";
import notificationIcone from "../../../assets/notification.png";
import { Navigate, useNavigate } from "react-router-dom";
import { RetornarInfoUser } from "../../context/info-user.context";

export function Header() {

    const navigate = useNavigate();
    
    const [user, setUser] = useState({});

    useEffect(() => {
        setUser(RetornarInfoUser());
    }, []);
    function handlerChange(handler) {
    }
    function handlerBackHome() {
        navigate('/home')
    }

    return (
        <header>
            <div className="header-left">
                <img onClick={() => navigate("/inicio")} src={imagemIcone} alt="" style={{marginRight: '20px'}} />
                <h2  onClick={() =>  navigate("/inicio")} style={{fontSize: '22px'}}>Inicio</h2>
                <h2  onClick={() =>  navigate("/procurar")} style={{fontSize: '22px'}}>Procurar Aulas</h2>
            </div>
            <div className="header-center ">
                <img className="imagem-center" src={imagemIcone} alt="" />
            </div>
            <div className="header-right">
                <nav className="nav-header">
                    <ul>
                        <li><h2>{user.saldo}</h2></li>
                        <li><a ><img src={notificationIcone} alt="" /></a></li>
                        <li><h2  onClick={() => navigate("/login")}>Logout</h2></li>
                    </ul>
                </nav>
            </div>
        </header>
    );
}
