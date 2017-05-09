import React, { Component, PropTypes } from 'react';
const propTypes = {
    name: React.PropTypes.string,
    href: React.PropTypes.string
};
const defaultProps = {
    name: "default tag name",
    href: "default tag href"
};
class CardTag extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>
                <a href={this.props.href}>{this.props.name}</a>
            </div>
        );
    }
}
CardTag.propTypes = propTypes;
CardTag.defaultProps = defaultProps;
export default CardTag;
