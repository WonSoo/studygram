import React, { Component, PropTypes } from 'react';
import Search from './Search';
import MenuBar from './MenuBar';
import logo from '../logo.png';
import '../App.css'

const propTypes = {
};
const defaultProps = {
};
class Header extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>
                <header>
                    <img src={logo} className="logoImg" alt="logo"/>
                    <Search></Search>
                    <MenuBar/>
                </header>
            </div>
        );
    }
}
Header.propTypes = propTypes;
Header.defaultProps = defaultProps;

export default Header;
