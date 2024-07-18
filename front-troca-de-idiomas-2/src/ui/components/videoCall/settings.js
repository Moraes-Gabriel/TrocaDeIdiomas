import { createClient, createMicrophoneAndCameraTracks } from "agora-rtc-react";

const appId = "16343406d00d400a923c1d66bf5ae1b3";
//const token ="007eJxTYDiyRDnW4bsb30/7DXzd207LTr22OFTp++myT61brx4/G/legcHSwNgwLSXZ1DwpycAk0dDYMtHCxNLUMMUw1dA41SDV5K0ja0pDICPDl+6XrIwMEAjiszDkJmbmMTAAAAPIIkY=";
const token ="007eJxTYNB9xv3jlen/TfeubH55Lz1kZ/XKPtNfteLhbX8/fc3+v/CIAoOlgbFhWkqyqXlSkoFJoqGxZaKFiaWpYYphqqFxqkGqSXc8a0pDICND6P9PDIxQCOIzMhgyMAAAYKYiww==";

export const config = { mode: "rtc", codec: "vp8", appId: appId, token: token };
export const useClient = createClient(config);
export const useMicrophoneAndCameraTracks = createMicrophoneAndCameraTracks();
export const channelName = "main";
