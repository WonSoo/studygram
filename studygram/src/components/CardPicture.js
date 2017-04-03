import React, { Component, PropTypes } from 'react';

const propTypes = {
    picture: React.PropTypes.object
};
const defaultProps = {
};
class CardPicture extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>
                <img src={this.props.picture} alt="main"/>
            </div>
        );
    }
}
CardPicture.propTypes = propTypes;
CardPicture.defaultProps = defaultProps;
export default CardPicture;
