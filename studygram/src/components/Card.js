import React, { Component, PropTypes } from 'react';
import CardHeader from './CardHeader';
import CardPicture from './CardHeader';
import CardTagContainer from './CardTagContainer';
import CardArticle from './CardArticle';


const propTypes = {
};
const defaultProps = {
};
class Card extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>
                <CardHeader name={this.state.name} time={this.state.time}/>
                <CardPicture picture={this.state.picture}/>
                <CardTagContainer/>
                <CardArticle/>
            </div>
        );
    }
}
Card.propTypes = propTypes;
Card.defaultProps = defaultProps;
export default Card;
