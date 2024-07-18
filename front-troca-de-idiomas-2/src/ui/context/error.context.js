import createGlobalState from "react-create-global-state";

const stringifyError = localStorage.getItem("error");

const error = JSON.parse(stringifyError) || {};

const [useGlobalError, ErrorGlobalProvider] = createGlobalState(error);

export { useGlobalError, ErrorGlobalProvider };
