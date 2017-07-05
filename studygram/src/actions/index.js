export const ADD_GRAM = 'ADD_GRAM';
export const SET_GRAM = 'SET_GRAM';

export function addGram() {
    return {
        type: ADD_GRAM
    };
}

export function setGram() {
    return {
        type: SET_GRAM
    };
}
