import React, { Component, PropTypes } from 'react';
const propTypes = {
    content: React.PropTypes.string
};
const defaultProps = {
    content: "default content string",
    title: "default title string"
};
class CardArticle extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div className="Card-Article-Div">
                <h5>{this.props.title}</h5>
                <p>{this.props.content}</p>
            </div>
        );
    }
}
CardArticle.propTypes = propTypes;
CardArticle.defaultProps = defaultProps;
export default CardArticle;
