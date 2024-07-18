import { useState } from 'react';
import './styles.css';
import TimePicker from 'react-time-picker';
import "react-datepicker/dist/react-datepicker.css";
import { useProfsApi } from '../../hooks/api/professor/use-profs-api.hook';

import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { SolicitarProfessor } from '../../components/SolicitarProfessor/SolicitarProfessor';
import DatePicker from 'react-date-picker';
function ProcurarAgendaPage() {

  const [languages] = useState([
    'Portugues', 'Ingles', 'Espanhol', 'Italiano', 'Alemao', 'Frances', 'Japones', 'Chines',
    'Coreano', 'Russo', 'Arabe', 'Hindi', 'Holandes', 'Polones', 'Turco', 'Ucraniano',
    'Sueco', 'Noruegues', 'Dinamarques', 'Finlandes', 'Grego', 'Hebraico', 'Hungaro',
    'Indonesio', 'Lituano', 'Malaio', 'Mandarim', 'Mongol', 'Persa', 'Polones', 'Romeno',
    'Servio', 'Tailandes', 'Tcheco', 'Vietnamita'
  ]);
  const [users, setUsers] = useState([]);
  const [selectedDate, setSelectedDate] = useState(new Date());


  const [data, setData] = useState({ language: '', date: new Date(), startTime: '09:00', endTime: '12:00' })
  const useProfs = useProfsApi();

  async function handleSolicitarClick(event) {
    event.preventDefault();
    const response = await useProfs.listarProfessoresDisponivesFiltrado(data.language, data);
    setUsers(response);
    toast("Professores procurados com sucesso!")
  }

  const today = new Date();
  const nextWeek = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000);


  return (
    <div className="container">
      <main >
        <div className="form">
          <div className='div-select'>
            <h2>Lingua</h2>
            <select className="language-select" value={data.language} onChange={(e) => setData({ ...data, language: e.target.value })}>
              <option value="">Selecione um idioma</option>
              {languages.map((language, index) => (
                <option key={index} value={language}>
                  {language}
                </option>
              ))}
            </select>
          </div>
          <div>
            <h2>Dia</h2>
            <DatePicker
              selected={data?.date}
              onChange={(d) => {
                setData((prevState) => {
                  return { ...prevState, date: new Date(d) };
                });
              }}
              minDate={today}
              maxDate={nextWeek}
              dateFormat="dd/MM/yyyy"
            />
          </div>
          <div className="dataPicker">
            <div>
              <h2>Inicio</h2>
              <TimePicker onChange={(e) => setData({ ...data, startTime: e })} value={data.startTime} />
            </div>

            <div>
              <h2>Fim</h2>
              <TimePicker onChange={(e) => setData({ ...data, endTime: e })} value={data?.endTime} />
            </div>

          </div>
          <button className='btn-buscar' onClick={handleSolicitarClick}>Buscar</button>
        </div>
      </main>

      <section>
        <ul>
          {users?.map((user, index) => {
            return <SolicitarProfessor user={user} idioma={data.language} date={data.date} startTime={data.startTime} key={index} />
          })}
        </ul>
      </section>
    </div>
  );
}


export default ProcurarAgendaPage;