import React, { Component, PropTypes } from 'react';
const propTypes = {
};
const defaultProps = {
};
class Tag extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div className="Card-Tag-Div">
                <a className="Card-Tag" href="">{this.props.name}</a>
            </div>
        );
    }
}
Tag.propTypes = propTypes;
Tag.defaultProps = defaultProps;
export default Tag;
