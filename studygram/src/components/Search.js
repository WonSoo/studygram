import React, { Component, PropTypes } from 'react';
import Axios from 'axios';

const propTypes = {
};
const defaultProps = {
};
class Search extends Component {
    constructor(props) {
        super(props);
        this.onTextInputChange = this.onTextInputChange.bind(this);
        this.state = {
            keyWord: ""
        }
    }

    onTextInputChange(e) {
        this.setState({
            keyWord: e.target.value
        });
    }

    sendSearchKeyWord() {

    }

    render() {
        return(
            <div className="Search-div">
                <input className="Search-input" type="text" placeholder="검색" onChange={this.onTextInputChange}/>

            </div>
        );
    }
}
Search.propTypes = propTypes;
Search.defaultProps = defaultProps;
export default Search;
