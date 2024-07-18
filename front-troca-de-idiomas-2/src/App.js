import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './ui/screen/login/LoginPage';
import CadastroPage from './ui/screen/cadastroPage/CadastroPage';
import ProcurarAgendaPage from './ui/screen/procurarAgendaParaEnsinar/procurarAgendaPage';
import { Header } from './ui/components/header/Header';
import { useGlobalUser } from './ui/context/user.context';

import { ToastContainer } from 'react-toastify';
import { Inicio } from './ui/screen/inicio/Inicio';
import { Aula } from './ui/screen/aula/Aula';

function App() {

  const [user] = useGlobalUser();

  function PrivateRoute({ children }) {
    console.log(!!user);
    if (user) {
      return (
        <>
          <Header />
          {children}
        </>
      );
    } else {
      return <Navigate to="/login" />;
    }
  }
  return (
    <>
    <ToastContainer />

      <Routes>
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/cadastro" element={<PrivateRoute><CadastroPage/></PrivateRoute>} />
        <Route path="/procurar" element={<PrivateRoute><ProcurarAgendaPage/></PrivateRoute>} />
        <Route path="/inicio" element={<PrivateRoute><Inicio/></PrivateRoute>} />
        <Route path="/aula/:aulaId" element={<PrivateRoute><Aula/></PrivateRoute>} />
      </Routes>
    </>
  );
}

export default App;