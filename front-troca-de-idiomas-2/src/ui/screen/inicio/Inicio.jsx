import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { RetornarInfoUser } from '../../context/info-user.context';
import { useGlobalUser } from '../../context/user.context';
import { useProfsApi } from '../../hooks/api/professor/use-profs-api.hook';
import Man from '../../../assets/man.png';
import './style.css';
import { useSolicitacaoAula } from '../../hooks/api/solicitacaoAula/use-solicitacaoAula-api.hook';
import { useAula } from '../../hooks/api/aula/use-Aula-api.hook';
import StarRatings from 'react-star-ratings';
import { toast } from "react-toastify";
export function Inicio() {
    const [solicitacoes, setSolicitacoes] = useState([]);
    const [aulasAgendadas, setAulasAgendadas] = useState([]);
    const [aulasConcluidas, setAulasConcluidas] = useState([]);
    const [aulasEncerradas, setAulasEncerradas] = useState([]);
    const [tabelaSelecionada, setTabelaSelecionada] = useState(0);
    const [rating, setRating] = useState();
    const navigate = useNavigate();
    const infoUSer = RetornarInfoUser();
    const useSolicitacao = useSolicitacaoAula();
    const useAulasApi = useAula();
    const [resetarUseEffect, setResetarUseEffect] = useState(false);
    useEffect(() => {
        listarSolicitacoes();
        listarAulasAgendadas();
        listarAulasConcluidas();
        listarAulasEncerradas();
    }, []);

    useEffect(() => {

    }, [resetarUseEffect]);
    async function listarSolicitacoes() {

        if (infoUSer.role == 'PROFESSOR') {
            const response = await useSolicitacao.listarSolicitacoesRecebidas();
            setSolicitacoes(response);
        }

        if (infoUSer.role == 'ALUNO') {
            const response = await useSolicitacao.listarSolicitacoesEnviadas();
            setSolicitacoes(response);
        }
    }
    async function listarAulasAgendadas() {
        const response = await useAulasApi.listarAgendadas();
        setAulasAgendadas(response);
    }
    async function listarAulasConcluidas() {
        const response = await useAulasApi.listarConcluidas();
        setAulasConcluidas(response);
    }
    async function listarAulasEncerradas() {
        const response = await useAulasApi.listarEncerradas();
        setAulasEncerradas(response);
    }

    async function aceitarSolicitacao(id) {
        try {
            await useSolicitacao.aceitarSolicitacaoAula(id);
            const solictacao = solicitacoes.filter((solicitacao) => solicitacao.id != id);
            setSolicitacoes(solictacao);
        } catch (error) {

        }
        setResetarUseEffect(!resetarUseEffect);


    }

    async function recusarSolicitacao(id) {
        try {
            await useSolicitacao.recusarSolicitacaoAula(id);
            const solictacao = solicitacoes.filter((solicitacao) => solicitacao.id != id);
            setSolicitacoes(solictacao);
        } catch (error) {

        }
        setResetarUseEffect(!resetarUseEffect);

    }
    async function cancelarSolicitacao(id) {
        console.log("teste");
        await useSolicitacao.cancelarSolicitacao(id);
        setResetarUseEffect(!resetarUseEffect);
    }

    function changeRating(newRating, name) {
        setRating(newRating);
    }

    async function FAvaliarAula(aulaId) {
        console.log(aulaId);
        await useAulasApi.concluirAula(aulaId, rating);
        setResetarUseEffect(!resetarUseEffect);
    }

    async function cancelarAula(aulaId) {
        if (infoUSer.role == 'ALUNO') {
            await useAulasApi.alunoCancelarAula(aulaId);
        }
        if (infoUSer.role == 'PROFESSOR') {
            await useAulasApi.professorCancelarAula(aulaId);
        }
        toast('Aula cancelada com sucesso', { type: 'success' });
        setResetarUseEffect(!resetarUseEffect);

    }
    return (
        <div className='page'>

            <main>
                <div className='tabela-soliticao'>
                    {infoUSer.role == 'PROFESSOR' && <ul className='ul-inicio'>
                        Solicitacoes recebidas
                        {solicitacoes?.map((solicitacao, index) => {
                            return (
                                <li key={index} className='card'>
                                    <div className='man'>
                                        <img src={Man} alt="" />
                                    </div>
                                    <div className='informacao-aluno' style={{ margin: '0px 10px 0px 10px ' }}>
                                        <h3>{solicitacao?.aluno?.firstName} {solicitacao?.aluno?.lastName}</h3>
                                        <p>Idioma: {solicitacao?.idioma}</p>
                                        <p>Nivel: {solicitacao?.aluno?.nivel}</p>

                                    </div>
                                    <div className='disponibilidade'>
                                        <h2 style={{ padding: '0px' }}>Inicio: {new Date(solicitacao?.dataHoraInicio).getHours()}</h2>
                                        <h2 style={{ padding: '0px' }}>Fim: {new Date(solicitacao?.dataHoraFim).getHours()}</h2>
                                    </div>

                                    <div className='div-ver-detalhes'>
                                        <button onClick={() => aceitarSolicitacao(solicitacao?.id)}>Aceitar</button>
                                        <button onClick={() => recusarSolicitacao(solicitacao?.id)}>Recusar</button>
                                    </div>
                                </li>
                            )
                        })}
                    </ul>}
                    {infoUSer.role == 'ALUNO' && <ul>
                        <div className='titulo-tabelas'>
                            <h2>
                                Solicitacoes Enviadas
                            </h2>
                        </div>
                        {solicitacoes?.map((solicitacao, index) => {
                            return (
                                <li key={index} className='card'>
                                    <div className='man'>
                                        <img src={Man} alt="" />
                                    </div>
                                    <div className='informacao-aluno' style={{ margin: '0px 10px 0px 10px ' }}>
                                        <h3>{solicitacao?.professor?.firstName} {solicitacao?.professor?.lastName}</h3>
                                        <p>Idioma: {solicitacao?.idioma}</p>

                                    </div>
                                    <div className='disponibilidade'>
                                        <h2 style={{ padding: '0px' }}>Inicio: {new Date(solicitacao?.dataHoraInicio).getHours()}</h2>
                                        <h2 style={{ padding: '0px' }}>Fim: {new Date(solicitacao?.dataHoraFim).getHours()}</h2>
                                    </div>

                                    <div className='div-ver-detalhes'>
                                        <h2>Solicitacao Pendente</h2>
                                    </div>
                                    <div className='div-ver-detalhes'>
                                        <button onClick={() => cancelarSolicitacao(solicitacao?.id)}>Cancelar</button>
                                    </div>
                                </li>
                            )
                        })}
                    </ul>}

                </div>
            </main>
            <section>
                <div className='tabela-soliticao'>
                    <ul>
                        <ul className='titulo-tabelas' style={{ justifyContent: 'space-around' }}>
                            <li className={tabelaSelecionada === 0 ? 'selected' : ''} onClick={() => setTabelaSelecionada(0)}>Aulas Agendadas</li>
                            <li className={tabelaSelecionada === 1 ? 'selected' : ''} onClick={() => setTabelaSelecionada(1)}>Aulas Concluidas</li>
                            <li className={tabelaSelecionada === 2 ? 'selected' : ''} onClick={() => setTabelaSelecionada(2)}>Aulas Encerradas</li>

                        </ul>
                        {tabelaSelecionada === 0 && aulasAgendadas?.map((aula, index) => {
                            return (
                                <li key={index} className='card'>
                                    <div className='man'>
                                        <img src={Man} alt="" />
                                    </div>
                                    {infoUSer.role == "PROFESSOR" && <div className='informacao-aluno' style={{ margin: '0px 10px 0px 10px ' }}>
                                        <h3>{aula?.aluno?.firstName} {aula?.aluno?.lastName}</h3>
                                        <p>Idioma: {aula?.idioma}</p>
                                        <p>Nivel: {aula?.aluno?.nivel}</p>

                                    </div>}
                                    {infoUSer.role == "ALUNO" && <div className='informacao-aluno' style={{ margin: '0px 10px 0px 10px ' }}>
                                        <h3>{aula?.professor?.firstName} {aula?.professor?.lastName}</h3>
                                        <p>Idioma: {aula?.idioma}</p>
                                    </div>}
                                    <div className='disponibilidade'>
                                        <h2 style={{ padding: '0px' }}>Hora Inicio: {new Date(aula?.dataHoraInicio).getHours()}</h2>
                                        <h2 style={{ padding: '0px' }}>Hora Fim: {new Date(aula?.dataHoraFim).getHours()}</h2>
                                    </div>

                                    <div className='botao-entrar'>
                                        <button onClick={() => cancelarAula(aula?.id)}>Cancelar Aula</button>
                                        <button style={{ marginLeft: '20px' }} onClick={() => navigate(`/aula/${aula.id}`)}>Entrar</button>
                                    </div>
                                </li>
                            )
                        })}
                        {tabelaSelecionada === 1 && aulasConcluidas?.map((aula, index) => {
                            return (
                                <li key={index} className='card'>
                                    <div className='man'>
                                        <img src={Man} alt="" />
                                    </div>
                                    <div className='informacao-aluno' style={{ margin: '0px 10px 0px 10px ' }}>
                                        <h3>{aula?.aluno?.firstName} {aula?.aluno?.lastName}</h3>
                                        <p>Idioma: {aula?.idioma}</p>
                                        <p>Nivel: {aula?.aluno?.nivel}</p>

                                    </div>
                                    <div className='disponibilidade'>
                                        <h2 style={{ padding: '0px' }}>Hora Inicio: {new Date(aula?.dataHoraInicio).getHours()}</h2>
                                        <h2 style={{ padding: '0px' }}>Hora Fim: {new Date(aula?.dataHoraFim).getHours()}</h2>
                                    </div>

                                    <StarRatings
                                        rating={aula.nota}
                                        starRatedColor="blue"
                                        numberOfStars={4}
                                        name='rating'
                                    />
                                </li>
                            )
                        })}
                        {tabelaSelecionada === 2 && aulasEncerradas?.map((aula, index) => {
                            return (
                                <li key={index} className='card'>
                                    <div className='man'>
                                        <img src={Man} alt="" />
                                    </div>
                                    <div className='informacao-aluno' style={{ margin: '0px 10px 0px 10px ' }}>
                                        <h3>{aula?.aluno?.firstName} {aula?.aluno?.lastName}</h3>
                                        <p>Idioma: {aula?.idioma}</p>
                                        <p>Nivel: {aula?.aluno?.nivel}</p>

                                    </div>
                                    <div className='disponibilidade'>
                                        <h2 style={{ padding: '0px' }}>Hora Inicio: {new Date(aula?.dataHoraInicio).getHours()}</h2>
                                        <h2 style={{ padding: '0px' }}>Hora Fim: {new Date(aula?.dataHoraFim).getHours()}</h2>
                                    </div>

                                    {infoUSer.role == 'ALUNO' && <div style={{ display: 'flex' }}>
                                        <StarRatings
                                            rating={rating}
                                            starRatedColor="blue"
                                            changeRating={changeRating}
                                            numberOfStars={4}
                                            name='rating'
                                        />
                                        <div className='botao-entrar'>
                                            <button onClick={() => FAvaliarAula(aula.id)}>Avaliar</button>
                                        </div>
                                    </div>}
                                </li>
                            )
                        })}
                    </ul>
                </div>
            </section>
        </div>

    );
}
