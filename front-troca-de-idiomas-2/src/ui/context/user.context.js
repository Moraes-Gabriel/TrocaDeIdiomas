import createGlobalState from 'react-create-global-state'

const KEY_LOCALSTORAGE = 'token'

const pegandoLocalStorage = typeof localStorage !== 'undefined' ? localStorage.getItem(KEY_LOCALSTORAGE) : null;
const valorInicial = pegandoLocalStorage ? JSON.parse(pegandoLocalStorage) : {};
const [useUsuario,  UserGlobalProvider] = createGlobalState(valorInicial)

function useGlobalUser() {
  const [usuario, setUsuario] = useUsuario()
  
  function setInformacoesUsuario(valorRecebido) {
    setUsuario(valorRecebido)
    localStorage.setItem(KEY_LOCALSTORAGE, JSON.stringify(valorRecebido))
  }
  return [usuario, setInformacoesUsuario]
}

export { useGlobalUser, UserGlobalProvider }