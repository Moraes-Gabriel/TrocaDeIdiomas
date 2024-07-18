import React, { useEffect, useState } from 'react';
import './style.css';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { useParams } from "react-router-dom";
import { RetornarInfoUser } from '../../context/info-user.context';
var stompClient = null;

export function ChatAula() {
    const [privateChats, setPrivateChats] = useState(new Map());
    const infoUser = RetornarInfoUser();
    const { aulaId } = useParams();
    const [chatMessages, setChatMessages] = useState([]);
    const [userData, setUserData] = useState({
        username: '',
        connected: false,
        message: ''
    });

    useEffect(() => {
        connect();
    }, []);
    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = Stomp.over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        setUserData({ ...userData, "connected": true });
        stompClient.subscribe('/user/' + infoUser.usuarioId + '/aula/' + aulaId, onPrivateMessage);
    }

    const onPrivateMessage = (payload) => {
        var payloadData = JSON.parse(payload.body);
        chatMessages.push(payloadData);
        setChatMessages([...chatMessages]);
    }

    const onError = (err) => {
        console.log(err);
    }

    const handleMessage = (event) => {
        const { value } = event.target;
        setUserData({ ...userData, "message": value });
    }

    const sendPrivateValue = () => {
        if (stompClient) {
            var chatMessage = {
                message: userData.message,
                userTipo: infoUser.role,
                userId: infoUser.usuarioId,
                status: "MESSAGE"
            };

            chatMessages.push(chatMessage)
            stompClient.send("/app/aula/" +aulaId, {}, JSON.stringify(chatMessage));
            setUserData({ ...userData, "message": "" });
        }
    }

    return (
        <div className="">
            <div className="chat-box">
                <div className="chat-content">
                    <ul className="chat-messages">
                        {chatMessages?.map((chat, index) => (
                            <li className={`message ${chat?.sender?.id === infoUser.usuarioId && "self"}`} key={index}>
                                {chat.sender?.id !== userData.id && <div className="avatar">{chat?.sender?.lastName}</div>}

                                <div className="message-data">{chat.message}</div>
                                {chat?.sender?.id === userData.id && <div className="avatar self">Você</div>}
                            </li>
                        ))}
                    </ul>

                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} />
                        <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                    </div>
                </div>
            </div>

        </div>

    );
}