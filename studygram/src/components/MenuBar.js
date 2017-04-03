import React, { Component, PropTypes } from 'react';
import list from '../list.png';
import account from '../account';

const propTypes = {
};
const defaultProps = {
};
class MenuBar extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>
            <a><img src={list} alt="list"/></a>
            <a><img src={account} alt="account"/></a>
            <a><img src={list} alt="list"/></a>
            </div>
        );
    }
}
MenuBar.propTypes = propTypes;
MenuBar.defaultProps = defaultProps;
export default MenuBar;
