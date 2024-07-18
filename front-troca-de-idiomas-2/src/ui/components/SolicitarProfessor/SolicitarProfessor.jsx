import "./style.css";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { RetornarInfoUser } from "../../context/info-user.context";
import Man from "../../../assets/man.png"
import Checked from "../../../assets/checked.png"
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import TimePicker from 'react-time-picker';
import { useSolicitacaoAula } from "../../hooks/api/solicitacaoAula/use-solicitacaoAula-api.hook";

export function SolicitarProfessor({ user, date,idioma, index }) {

    const [startTime, setStartTime] = useState('10:00');
    const useSolicitacao = useSolicitacaoAula();
    async function solicitarProfessor(professorId) {
        await useSolicitacao.mandarSolictiacaoDeAula(professorId, date , startTime, idioma);
    }
    useEffect(() => {
        console.log(typeof date);
    }, [user]);
    return (
        <>
            <li key={index} className='card'>
                <div className='man'>
                    <img src={Man} alt="" />
                </div>
                <div className='informacao-professor' style={{ margin: '0px 10px 0px 10px ' }}>
                    <h3>{user?.firstName} {user?.lastName}</h3>
                    <p>Idioma: {idioma}</p>
                    <p>Valor Hora: {user?.valorPorHora}</p>
                </div>
                <div className='disponibilidade'>
                    <h2>Inicio: {user.disponibilidade?.horaInicio}</h2>
                    <h2>Fim: {user?.disponibilidade?.horaFim}</h2>
                    <h2>Dia: {date.getDay()}</h2>
                </div>

                <div style={{ marginLeft: '20px' }}>
                    <h2>Horas deseja iniciar</h2>
                    <TimePicker onChange={(e) => setStartTime(e)} value={startTime} />
                </div>
                <div className='div-ver-detalhes'>
                    <button onClick={() => solicitarProfessor(user?.id)}>Solicitar</button>
                </div>


                <div style={{ display: 'flex', alignItems: 'center', marginLeft: '20px' }}>
                    <img src={Checked} alt="" />
                </div>
            </li>
        </>
    );



}
