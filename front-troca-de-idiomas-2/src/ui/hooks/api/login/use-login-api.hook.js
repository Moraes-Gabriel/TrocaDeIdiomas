import { useMemo } from "react";
import { useGlobalUser } from "../../../context/user.context";
import { useHttp } from "../_base/use-http.hook";


export function useLoginApi() {

  const [user,] = useGlobalUser();

  const API_URL = "http://localhost:8080";

  const httpInstance = useHttp(API_URL, {});

  async function logar(email, password) {

    const data = {username: email, password: password}
      const response = httpInstance.login('/login',
      {},
      {
        auth: data
      })
      return response;
      console.log(response);
  }

  async function logout() {
    const response = await httpInstance.post('/logout', 
     {} ,
    {
      headers: {
        'X-Auth-Token': user
      }
    });
    return response;
  } 

  async function register(inputValues) {
    try{
      const response = httpInstance.post('/usuario',
      inputValues,
      {})

      return response;
      
    }catch(error){
    }
}
  
  

  return useMemo(
    () => ({
      logar,
      register,
      logout
    }),
    [user]
  );
}
