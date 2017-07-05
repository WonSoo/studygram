import {
    ADD_GRAM,
    SET_GRAM
} from '../actions';
import {
    combineReducers
} from 'redux';

const gramInitialState = {
    grams: []
};

const gramReducer = (state = gramInitialState, action) => {
    switch (action.type) {
        case ADD_GRAM:
            return Object.assign({}, state, {
                grams: [
                    ...state.grams,
                    ...action.grams
                ]
            });
        case SET_GRAM:
            return Object.assign({}, state, {
                grams: action.grams
            });
        default:
            return state;
    }
};



const studygramApp = combineReducers({
    gram: gramReducer
});

export default studygramApp;
