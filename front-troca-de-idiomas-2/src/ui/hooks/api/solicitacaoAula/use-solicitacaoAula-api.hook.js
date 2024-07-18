import { useMemo } from "react";
import { useGlobalUser } from "../../../context/user.context";
import { useHttp } from "../_base/use-http.hook";
import { url } from "../../../context/API_URL.js"
import { Toast } from "react-bootstrap";
import { toast } from "react-toastify";

export function useSolicitacaoAula() {

  const [user,] = useGlobalUser();

  const API_URL = url + "/solicitacoes";

  const httpInstance = useHttp(API_URL, {});


  async function mandarSolictiacaoDeAula(idProfessor, date, startTime, idioma) {
    const hour = startTime.split(":")[0];
    const minute = startTime.split(":")[1];
    let dataRequest = new Date(date)
    dataRequest.setHours(hour-3);
    dataRequest.setMinutes(minute);

    const solicitacaoAulaRequest = { idProfessor, dataHoraInicio: dataRequest, idioma }
    try {
      const response = await httpInstance.post(
        `/aula`,
        solicitacaoAulaRequest,
        {
          headers: {
            'X-Auth-Token': user
          }
        },
      );
      toast("sucesso ao solicitar aula");
      return response;
    } catch (error) {
      console.log(error);
      toast("erro ao solicitar aula");
    }
  }
  async function aceitarSolicitacaoAula(solicitacaoAulaId) {

    const response = await httpInstance.put(
      `/${solicitacaoAulaId}/aceitar`,
      {},
      {
        headers: {
          'X-Auth-Token': user
        }
      },
    );

    return response;
  }
  async function recusarSolicitacaoAula(solicitacaoAulaId) {

    const response = await httpInstance.put(
      `/${solicitacaoAulaId}/recusar`,
      {},
      {
        headers: {
          'X-Auth-Token': user
        }
      },
    );

    return response;
  }

  async function listarSolicitacoesRecebidas() {
    const response = await httpInstance.get(`/recebidas`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function listarSolicitacoesEnviadas() {
    const response = await httpInstance.get(`/enviadas`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function cancelarSolicitacao(solicitacaoAulaId) {

    const response = await httpInstance.put(
      `/${solicitacaoAulaId}/cancelar`,
      {},
      {
        headers: {
          'X-Auth-Token': user
        }
      },
    );

    return response;
  }

  return useMemo(
    () => ({
      mandarSolictiacaoDeAula,
      aceitarSolicitacaoAula,
      recusarSolicitacaoAula,
      listarSolicitacoesRecebidas,
      listarSolicitacoesEnviadas,
      cancelarSolicitacao
    }),
    [user]
  );
}
