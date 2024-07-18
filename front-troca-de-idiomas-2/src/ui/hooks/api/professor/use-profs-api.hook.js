import { useMemo } from "react";
import { useGlobalUser } from "../../../context/user.context";
import { useHttp } from "../_base/use-http.hook";
import { url } from "../../../context/API_URL.js"
import { format } from 'date-fns';
export function useProfsApi() {

  const [user,] = useGlobalUser();

  const API_URL = url + "/professores";

  const httpInstance = useHttp(API_URL, {});

  
  async function listarProfessoresDisponivesFiltrado(learningLanguage, data) {
    const formattedDate = format(data?.date, 'yyyy-MM-dd');
    const dataRequestBody = {
      dataHoraInicio: data?.startTime,
      dataHoraFim: data?.endTime,
      dia: formattedDate
    };
    console.log(dataRequestBody);
    const response = await httpInstance.get(`/available/${learningLanguage}`, {
      headers: {
        'X-Auth-Token': user
      }
      , params: dataRequestBody
    });
    return response;
  }

  return useMemo(
    () => ({
      listarProfessoresDisponivesFiltrado,

    }),
    [user]
  );
}
