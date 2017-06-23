import React, {Component, PropTypes} from 'react';
const propTypes = {};
const defaultProps = {};
class SearchResult extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        const convertToH3 = (list) => {
            return list.map((data, i) => {
                return (
                    <h3 key={i}>{data}</h3>
                )
            });
        }
        return (
            <div>
                {convertToH3(this.props.results)}
            </div>
        );
    }
}
SearchResult.propTypes = propTypes;
SearchResult.defaultProps = defaultProps;
export default SearchResult;
