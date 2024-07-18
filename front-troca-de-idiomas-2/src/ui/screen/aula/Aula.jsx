import React, { useEffect, useState } from 'react';
import './style.css';
import { toast } from "react-toastify";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { useGlobalUser } from '../../context/user.context';
import { RetornarInfoUser } from '../../context/info-user.context';
import { ChatAula } from '../../components/chatAula/ChatAula';
import VideoCall from '../../components/videoCall/VideoCall';
import { useNavigate, useParams } from 'react-router-dom';
import { useAula } from '../../hooks/api/aula/use-Aula-api.hook';
var stompClient = null;

export function Aula() {
    const [inCall, setInCall] = useState(false);
    const { aulaId } = useParams();
    const useAulaApi = useAula();
    const navigate = useNavigate();
    useEffect(() => {
        async function verificarSeEstaAutorizadoAParticiparAula() {
            const response = await useAulaApi.verificarSeEstaAutorizadoAParticiparDaAula(aulaId);
            if (!!!response) { 
            navigate('/inicio');
            toast('Você não está autorizado a participar dessa aula');
            }
        }

        verificarSeEstaAutorizadoAParticiparAula();

    }, []);
    return (
        <div className="aula">
            <div className='video'>
                {inCall ? (
                    <VideoCall setInCall={setInCall} id={aulaId} />
                ) : (
                    <button className='joinCallVideo' onClick={() => setInCall(true)}>Entrar na chamada</button>
                )}
            </div>

            <ChatAula />
        </div>

    );
}