import { useMemo } from "react";
import { useGlobalUser } from "../../../context/user.context";
import { useHttp } from "../_base/use-http.hook";
import { url } from "../../../context/API_URL.js"

export function useUsuarioApi() {

  const [user,] = useGlobalUser();

  const API_URL = url+"/usuario";
  

  const httpInstance = useHttp(API_URL, {});


  async function buscarUsuarioLogado() {
    const response = await httpInstance.get(``, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function criarUsuario( email, senha) {
    const usuarioObject = {email ,senha }
    const response = await httpInstance.post(
      `/`,
      usuarioObject,
      {},
    );

    return response;
  }
  async function pesquisarProfessoresDisponiveis(idioma, inicio, fim, dia) {
    const response = await httpInstance.get(`/pesquisar/${pesquisa}`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function pesquisarPorIdentify(identify) {
    const response = await httpInstance.get(`/pesquisar/identify/${identify}`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  async function verificarSeSaoAmigosPorIdentify(identify) {
    const response = await httpInstance.get(`verificar/amigos/identify/${identify}`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  
  async function seguir(identify) {
    const response = await httpInstance.put(`/seguir/${identify}`, 
    {},
    {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  async function deseguir(identify) {
    const response = await httpInstance.put(`/deseguir/${identify}`, 
    {},
    {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  
  async function aceitarSolicitacao(identify) {
    const response = await httpInstance.put(`/aceitar/solicitacao/${identify}`, 
    {},
    {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  
  async function recusarSolicitacao(identify) {
    const response = await httpInstance.put(`/recusar/solicitacao/${identify}`, 
    {},
    {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function listarNotificacaoUsuario() {
    const response = await httpInstance.get(`/notificacao`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }

  async function listarTodosUsuariosPage(page) {
    const pageUrl = `?page=${page}&size=2 `
    const response = await httpInstance.get(`/todos${pageUrl}`, {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  }
  

  return useMemo(
    () => ({
      criarUsuario,
      pesquisarPorIdentifyOrEmail,
      pesquisarPorIdentify,
      seguir,
      deseguir,
      verificarSeSaoAmigosPorIdentify,
      listarNotificacaoUsuario,
      aceitarSolicitacao,
      recusarSolicitacao,
      buscarUsuarioLogado,
      listarTodosUsuariosPage

    }),
    [user]
  );
}
