import React, { Component, PropTypes } from 'react';

class CardPicture extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div className="Card-Picture-Div">
                <img className="Card-Picture" src={this.props.picture} alt="main"/>
            </div>
        );
    }
}

export default CardPicture;
