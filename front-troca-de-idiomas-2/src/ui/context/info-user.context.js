


function RetornarInfoUser() {
  
    const KEY_LOCALSTORAGE = 'user'
    const pegandoLocalStorage = typeof localStorage !== 'undefined' ? localStorage.getItem(KEY_LOCALSTORAGE) : null;
    const valorInicial = pegandoLocalStorage ? JSON.parse(pegandoLocalStorage) : {};
    return valorInicial;
  }
  
  export {RetornarInfoUser};