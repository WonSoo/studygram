import React, { Component, PropTypes } from 'react';
const propTypes = {
};
const defaultProps = {
};
class Search extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div className="Search-div">
                <input className="Search-input" type="text" placeholder="검색"/>
            </div>
        );
    }
}
Search.propTypes = propTypes;
Search.defaultProps = defaultProps;
export default Search;
