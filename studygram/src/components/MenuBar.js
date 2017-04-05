import React, { Component, PropTypes } from 'react';
import list from '../img/list.svg';
import account from '../img/account.svg';
{ /*import list from '../list.png';
import account from '../account'; */ }

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
            <div className="Header-MenuBar">
            <a><img className="MenuBar-pic" src={list} alt="list"/></a>
            <a><img className="MenuBar-pic" src={account} alt="account"/></a>
            <a><img className="MenuBar-pic" src={list} alt="list"/></a>
            </div>
        );
    }
}
MenuBar.propTypes = propTypes;
MenuBar.defaultProps = defaultProps;
export default MenuBar;
