import React, {Component, PropTypes} from 'react';
import CardHeader from './CardHeader';
import CardPicture from './CardPicture';
import CardTagContainer from './CardTagContainer';
import CardArticle from './CardArticle';
import sanaPic from '../../img/sana.jpg'

const propTypes = {
    title: React.PropTypes.string,
    time: React.PropTypes.string,
    content: React.PropTypes.string,
    tags: React.PropTypes.array,
    name: React.PropTypes.string,
    picture: React.PropTypes.string,
    cardId: React.PropTypes.number
};
const defaultProps = {
    title: 'title',
    time: '1분전',
    name: 'momo',
    content: '나는 아니야',
    tags: ['momo', 'twice', 'gram']
};
class Card extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        console.info(this.props.picture);
        return (
            <div className="Card">
                <CardHeader name={this.props.name} time={this.props.time} cardId={this.props.cardId}/>
                <CardPicture picture={this.props.picture}/>
                <CardTagContainer tags={this.props.tags}/>
                <CardArticle content={this.props.content} title={this.props.title}/>
            </div>
        );
    }
}
Card.propTypes = propTypes;
Card.defaultProps = defaultProps;
export default Card;
