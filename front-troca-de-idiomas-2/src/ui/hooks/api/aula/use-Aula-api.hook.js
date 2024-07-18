import { useMemo } from "react";
import { useGlobalUser } from "../../../context/user.context";
import { useHttp } from "../_base/use-http.hook";
import { url } from "../../../context/API_URL.js"
import { Toast } from "react-bootstrap";
import { toast } from "react-toastify";

export function useAula() {

  const [user,] = useGlobalUser();

  const API_URL = url + "/aulas";

  const httpInstance = useHttp(API_URL, {});

  async function listarAgendadas() {
    const response = await httpInstance.get(`/agendadas`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function listarConcluidas() {
    console.log(user);
    const response = await httpInstance.get(`/concluidas`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  async function listarEncerradas() {
    console.log(user);
    const response = await httpInstance.get(`/encerradas`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function verificarSeEstaAutorizadoAParticiparDaAula(id) {
    const response = await httpInstance.get(`/${id}/verificar/se/esta/permitido`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  async function concluirAula(aulaId, rating) {
    const response = await httpInstance.put(`/${aulaId}/concluir/${rating}`,{}, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  async function alunoCancelarAula(aulaId) {
    const response = await httpInstance.put(`/${aulaId}/aluno/cancelar`,{}, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  async function professorCancelarAula(aulaId) {
    const response = await httpInstance.put(`/${aulaId}/professor/cancelar`,{}, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  return useMemo(
    () => ({
      listarAgendadas,
      listarConcluidas,
      verificarSeEstaAutorizadoAParticiparDaAula,
      listarEncerradas,
      concluirAula,
      alunoCancelarAula,
      professorCancelarAula
    }),
    [user]
  );
}
