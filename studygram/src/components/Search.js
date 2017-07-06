import React, {Component, PropTypes} from 'react';
import Axios from 'axios';
import SearchResult from './SearchResult'
import {Config} from '../resource';

const propTypes = {};
const defaultProps = {};

class Search extends Component {
    constructor(props) {
        super(props);
        this.state = {
            keyWord: "",
            searchResult: []
        }

        this.onTextInputChange = this.onTextInputChange.bind(this);
        this.sendSearchKeyWord = this.sendSearchKeyWord.bind(this);
    }
    onTextInputChange(e) {
        this.setState({keyWord: e.target.value});
        this.sendSearchKeyWord(e.target.value);
    }

    sendSearchKeyWord(searchText) {
        Axios({url: `${Config.ip}/api/getKeyword/${searchText}`, method: 'get'}).then((response) => {
            response.data.forEach((data, index) => {
                response.data[index] = JSON.parse(data);
            });
            this.setState({
                searchResult: response
            });
            console.log(response);
        }).catch((error) => {
            console.error(error);
        });
    }
    render() {
        return (
            <div className="Search-div">
                <input className="Search-input" type="text" placeholder="검색" onChange={this.onTextInputChange}/>
            </div>
        );
    }
}
Search.propTypes = propTypes;
Search.defaultProps = defaultProps;
export default Search;
