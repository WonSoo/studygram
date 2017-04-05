import React, { Component, PropTypes } from 'react';
import Tag from './Tag';

const propTypes = {
    tags: React.PropTypes.array
};
const defaultProps = {
};
class CardTagContainer extends Component {
    constructor(props) {
        super(props);
    }
    render() {
         /* tags String배열을 tag컴포넌트배열로 변환 */
        const convertToTag = (tagArr) => {
            return tagArr.map((tag) => {
                return (<Tag name={tag}/>)
            })
        }
        return(
            <div className="Tag-Container-Div">
                {convertToTag(this.props.tags)}
            </div>
        );
    }
}
CardTagContainer.propTypes = propTypes;
CardTagContainer.defaultProps = defaultProps;
export default CardTagContainer;
