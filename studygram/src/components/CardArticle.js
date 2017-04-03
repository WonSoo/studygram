import React, { Component, PropTypes } from 'react';
const propTypes = {
    content: React.PropTypes.sring
};
const defaultProps = {
    content: "default content string"
};
class CardArticle extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>{this.props.content}</div>
        );
    }
}
CardArticle.propTypes = propTypes;
CardArticle.defaultProps = defaultProps;
export default CardArticle;
